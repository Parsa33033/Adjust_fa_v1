import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, openFile, byteSize } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './adjust-tutorial.reducer';
import { IAdjustTutorial } from 'app/shared/model/adjust-tutorial.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IAdjustTutorialDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const AdjustTutorialDetail = (props: IAdjustTutorialDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { adjustTutorialEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="adjustApp.adjustTutorial.detail.title">AdjustTutorial</Translate> [<b>{adjustTutorialEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="title">
              <Translate contentKey="adjustApp.adjustTutorial.title">Title</Translate>
            </span>
          </dt>
          <dd>{adjustTutorialEntity.title}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="adjustApp.adjustTutorial.description">Description</Translate>
            </span>
          </dt>
          <dd>{adjustTutorialEntity.description}</dd>
          <dt>
            <span id="text">
              <Translate contentKey="adjustApp.adjustTutorial.text">Text</Translate>
            </span>
          </dt>
          <dd>{adjustTutorialEntity.text}</dd>
          <dt>
            <span id="thumbnail">
              <Translate contentKey="adjustApp.adjustTutorial.thumbnail">Thumbnail</Translate>
            </span>
          </dt>
          <dd>
            {adjustTutorialEntity.thumbnail ? (
              <div>
                {adjustTutorialEntity.thumbnailContentType ? (
                  <a onClick={openFile(adjustTutorialEntity.thumbnailContentType, adjustTutorialEntity.thumbnail)}>
                    <img
                      src={`data:${adjustTutorialEntity.thumbnailContentType};base64,${adjustTutorialEntity.thumbnail}`}
                      style={{ maxHeight: '30px' }}
                    />
                  </a>
                ) : null}
                <span>
                  {adjustTutorialEntity.thumbnailContentType}, {byteSize(adjustTutorialEntity.thumbnail)}
                </span>
              </div>
            ) : null}
          </dd>
          <dt>
            <span id="tokenPrice">
              <Translate contentKey="adjustApp.adjustTutorial.tokenPrice">Token Price</Translate>
            </span>
          </dt>
          <dd>{adjustTutorialEntity.tokenPrice}</dd>
          <dt>
            <Translate contentKey="adjustApp.adjustTutorial.video">Video</Translate>
          </dt>
          <dd>{adjustTutorialEntity.videoId ? adjustTutorialEntity.videoId : ''}</dd>
        </dl>
        <Button tag={Link} to="/adjust-tutorial" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/adjust-tutorial/${adjustTutorialEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ adjustTutorial }: IRootState) => ({
  adjustTutorialEntity: adjustTutorial.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(AdjustTutorialDetail);
