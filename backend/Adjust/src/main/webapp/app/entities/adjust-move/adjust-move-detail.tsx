import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, openFile, byteSize } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './adjust-move.reducer';
import { IAdjustMove } from 'app/shared/model/adjust-move.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IAdjustMoveDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const AdjustMoveDetail = (props: IAdjustMoveDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { adjustMoveEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="adjustApp.adjustMove.detail.title">AdjustMove</Translate> [<b>{adjustMoveEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="name">
              <Translate contentKey="adjustApp.adjustMove.name">Name</Translate>
            </span>
          </dt>
          <dd>{adjustMoveEntity.name}</dd>
          <dt>
            <span id="muscleName">
              <Translate contentKey="adjustApp.adjustMove.muscleName">Muscle Name</Translate>
            </span>
          </dt>
          <dd>{adjustMoveEntity.muscleName}</dd>
          <dt>
            <span id="muscleType">
              <Translate contentKey="adjustApp.adjustMove.muscleType">Muscle Type</Translate>
            </span>
          </dt>
          <dd>{adjustMoveEntity.muscleType}</dd>
          <dt>
            <span id="equipment">
              <Translate contentKey="adjustApp.adjustMove.equipment">Equipment</Translate>
            </span>
          </dt>
          <dd>{adjustMoveEntity.equipment}</dd>
          <dt>
            <span id="picture">
              <Translate contentKey="adjustApp.adjustMove.picture">Picture</Translate>
            </span>
          </dt>
          <dd>
            {adjustMoveEntity.picture ? (
              <div>
                {adjustMoveEntity.pictureContentType ? (
                  <a onClick={openFile(adjustMoveEntity.pictureContentType, adjustMoveEntity.picture)}>
                    <img
                      src={`data:${adjustMoveEntity.pictureContentType};base64,${adjustMoveEntity.picture}`}
                      style={{ maxHeight: '30px' }}
                    />
                  </a>
                ) : null}
                <span>
                  {adjustMoveEntity.pictureContentType}, {byteSize(adjustMoveEntity.picture)}
                </span>
              </div>
            ) : null}
          </dd>
        </dl>
        <Button tag={Link} to="/adjust-move" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/adjust-move/${adjustMoveEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ adjustMove }: IRootState) => ({
  adjustMoveEntity: adjustMove.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(AdjustMoveDetail);
