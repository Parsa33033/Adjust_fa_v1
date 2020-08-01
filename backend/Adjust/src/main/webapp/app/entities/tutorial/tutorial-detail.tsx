import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, openFile, byteSize } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './tutorial.reducer';
import { ITutorial } from 'app/shared/model/tutorial.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ITutorialDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const TutorialDetail = (props: ITutorialDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { tutorialEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="adjustApp.tutorial.detail.title">Tutorial</Translate> [<b>{tutorialEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="title">
              <Translate contentKey="adjustApp.tutorial.title">Title</Translate>
            </span>
          </dt>
          <dd>{tutorialEntity.title}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="adjustApp.tutorial.description">Description</Translate>
            </span>
          </dt>
          <dd>{tutorialEntity.description}</dd>
          <dt>
            <span id="text">
              <Translate contentKey="adjustApp.tutorial.text">Text</Translate>
            </span>
          </dt>
          <dd>{tutorialEntity.text}</dd>
          <dt>
            <span id="thumbnail">
              <Translate contentKey="adjustApp.tutorial.thumbnail">Thumbnail</Translate>
            </span>
          </dt>
          <dd>
            {tutorialEntity.thumbnail ? (
              <div>
                {tutorialEntity.thumbnailContentType ? (
                  <a onClick={openFile(tutorialEntity.thumbnailContentType, tutorialEntity.thumbnail)}>
                    <img
                      src={`data:${tutorialEntity.thumbnailContentType};base64,${tutorialEntity.thumbnail}`}
                      style={{ maxHeight: '30px' }}
                    />
                  </a>
                ) : null}
                <span>
                  {tutorialEntity.thumbnailContentType}, {byteSize(tutorialEntity.thumbnail)}
                </span>
              </div>
            ) : null}
          </dd>
          <dt>
            <span id="tokenPrice">
              <Translate contentKey="adjustApp.tutorial.tokenPrice">Token Price</Translate>
            </span>
          </dt>
          <dd>{tutorialEntity.tokenPrice}</dd>
          <dt>
            <span id="adjustTutorialId">
              <Translate contentKey="adjustApp.tutorial.adjustTutorialId">Adjust Tutorial Id</Translate>
            </span>
          </dt>
          <dd>{tutorialEntity.adjustTutorialId}</dd>
          <dt>
            <Translate contentKey="adjustApp.tutorial.video">Video</Translate>
          </dt>
          <dd>{tutorialEntity.videoId ? tutorialEntity.videoId : ''}</dd>
          <dt>
            <Translate contentKey="adjustApp.tutorial.client">Client</Translate>
          </dt>
          <dd>{tutorialEntity.clientId ? tutorialEntity.clientId : ''}</dd>
        </dl>
        <Button tag={Link} to="/tutorial" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/tutorial/${tutorialEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ tutorial }: IRootState) => ({
  tutorialEntity: tutorial.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(TutorialDetail);
