import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, openFile, byteSize } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './move.reducer';
import { IMove } from 'app/shared/model/move.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IMoveDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const MoveDetail = (props: IMoveDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { moveEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="adjustApp.move.detail.title">Move</Translate> [<b>{moveEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="name">
              <Translate contentKey="adjustApp.move.name">Name</Translate>
            </span>
          </dt>
          <dd>{moveEntity.name}</dd>
          <dt>
            <span id="muscleName">
              <Translate contentKey="adjustApp.move.muscleName">Muscle Name</Translate>
            </span>
          </dt>
          <dd>{moveEntity.muscleName}</dd>
          <dt>
            <span id="muscleType">
              <Translate contentKey="adjustApp.move.muscleType">Muscle Type</Translate>
            </span>
          </dt>
          <dd>{moveEntity.muscleType}</dd>
          <dt>
            <span id="equipment">
              <Translate contentKey="adjustApp.move.equipment">Equipment</Translate>
            </span>
          </dt>
          <dd>{moveEntity.equipment}</dd>
          <dt>
            <span id="picture">
              <Translate contentKey="adjustApp.move.picture">Picture</Translate>
            </span>
          </dt>
          <dd>
            {moveEntity.picture ? (
              <div>
                {moveEntity.pictureContentType ? (
                  <a onClick={openFile(moveEntity.pictureContentType, moveEntity.picture)}>
                    <img src={`data:${moveEntity.pictureContentType};base64,${moveEntity.picture}`} style={{ maxHeight: '30px' }} />
                  </a>
                ) : null}
                <span>
                  {moveEntity.pictureContentType}, {byteSize(moveEntity.picture)}
                </span>
              </div>
            ) : null}
          </dd>
          <dt>
            <span id="adjustMoveId">
              <Translate contentKey="adjustApp.move.adjustMoveId">Adjust Move Id</Translate>
            </span>
          </dt>
          <dd>{moveEntity.adjustMoveId}</dd>
        </dl>
        <Button tag={Link} to="/move" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/move/${moveEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ move }: IRootState) => ({
  moveEntity: move.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(MoveDetail);
